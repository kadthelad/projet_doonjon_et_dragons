import os
import re
from collections import defaultdict

# Couleurs par package (ajoute d'autres si besoin)
PKG_COLORS = [
    "#LightBlue", "#LightYellow", "#Thistle", "#LightPink", "#PaleGreen", "#LightCyan", "#Plum", "#Wheat", "#Orange"
]

def find_java_files(root):
    java_files = []
    for dirpath, _, filenames in os.walk(root):
        for f in filenames:
            if f.endswith(".java"):
                java_files.append(os.path.join(dirpath, f))
    return java_files

def extract_package(content):
    m = re.search(r'package\s+([a-zA-Z0-9_.]+);', content)
    return m.group(1) if m else ""

def extract_classes(content):
    classes = []
    regex = r'(public\s+)?(abstract\s+)?(class|interface|enum)\s+([A-Za-z0-9_]+)(\s+extends\s+([A-Za-z0-9_\.]+))?(\s+implements\s+([A-Za-z0-9_,\. ]+))?'
    for m in re.finditer(regex, content):
        kind = m.group(3)
        name = m.group(4)
        extends = m.group(6) or ""
        implements = m.group(8) or ""
        classes.append({
            "kind": kind,
            "name": name,
            "extends": extends,
            "implements": [s.strip() for s in implements.split(",") if s.strip()] if implements else [],
            "abstract": "abstract" in (m.group(0) or ""),
        })
    return classes

def extract_fields(content):
    # Ignore constants (static final) for attributes, keep them as fields
    field_regex = r'(public|protected|private)?\s+(static\s+final\s+)?([A-Za-z0-9_<>,\[\].]+)\s+([A-Za-z0-9_]+)\s*(=|;|\()'
    fields = []
    for m in re.finditer(field_regex, content):
        vis, _, typ, name, trail = m.groups()
        # skip method declarations
        if trail == "(":
            continue
        fields.append({
            "visibility": vis or "+",
            "type": typ.strip(),
            "name": name
        })
    return fields

def extract_methods(content):
    # Ignore constructors (they will have no return type, name == class)
    # Only public/protected/private or default (package)
    meth_regex = r'(?P<vis>public|protected|private)?\s+(?P<static>static\s+)?(?P<ret>[A-Za-z0-9_<>\[\].]+)\s+(?P<name>[A-Za-z0-9_]+)\s*\((?P<params>[^\)]*)\)'
    methods = []
    for m in re.finditer(meth_regex, content):
        name = m.group("name")
        ret = m.group("ret")
        vis = m.group("vis") or "+"
        static = "static" in (m.group("static") or "")
        params = m.group("params")
        # Skip if constructor (return type == class name, handled elsewhere)
        # Also skip if it's a field or inner class (very naive filter)
        if name == ret:
            continue
        methods.append({
            "visibility": vis,
            "static": static,
            "name": name,
            "return": ret,
            "params": params
        })
    return methods

def parse_java_file(path):
    with open(path, encoding="utf-8") as f:
        content = f.read()
    pkg = extract_package(content)
    class_defs = extract_classes(content)
    fields = extract_fields(content)
    methods = extract_methods(content)
    return {
        "package": pkg,
        "classes": class_defs,
        "fields": fields,
        "methods": methods,
        "path": path
    }

def group_by_package(files_data):
    pkgs = defaultdict(list)
    for fd in files_data:
        pkgs[fd["package"]].append(fd)
    return pkgs

def plantuml_class_block(cls, fields, methods):
    lines = []
    stereotype = ""
    if cls["kind"] == "interface":
        stereotype = "interface "
    elif cls["kind"] == "enum":
        stereotype = "enum "
    elif cls.get("abstract"):
        stereotype = "abstract class "
    else:
        stereotype = "class "
    lines.append(f'{stereotype}{cls["name"]} ' + "{")
    # Attributs
    for field in fields:
        vis = field["visibility"][0] if field["visibility"] else "+"
        lines.append(f'    {vis} {field["name"]} : {field["type"]}')
    # Méthodes
    for meth in methods:
        vis = meth["visibility"][0]
        static = "{static} " if meth["static"] else ""
        params = ", ".join([p.strip() for p in meth["params"].split(",") if p.strip()])
        lines.append(f'    {vis} {static}{meth["name"]}({params}) : {meth["return"]}')
    lines.append("}")
    return "\n".join(lines)

def plantuml_package_block(pkg, files, color):
    lines = [f'package "{pkg or "default"}" {color} {{']
    done = set()
    for f in files:
        for cls in f["classes"]:
            # Only one class per file for Java convention
            if cls["name"] in done: continue
            class_fields = [fld for fld in f["fields"]]
            class_methods = [meth for meth in f["methods"]]
            lines.append(plantuml_class_block(cls, class_fields, class_methods))
            done.add(cls["name"])
    lines.append("}")
    return "\n".join(lines)

def plantuml_inheritance(files_data):
    lines = []
    for f in files_data:
        for cls in f["classes"]:
            if cls["extends"]:
                lines.append(f'{cls["extends"]} <|-- {cls["name"]}')
            for impl in cls["implements"]:
                lines.append(f'{impl} <|.. {cls["name"]}')
    return "\n".join(lines)

def plantuml_associations(files_data):
    # Simple association: if a field type is another class in project, draw -- line
    all_classes = set()
    for f in files_data:
        for c in f["classes"]:
            all_classes.add(c["name"])
    lines = []
    for f in files_data:
        for cls in f["classes"]:
            for field in f["fields"]:
                typ = field["type"]
                typ_clean = typ.replace("List<", "").replace(">", "").replace("ArrayList<", "")
                if typ_clean in all_classes and typ_clean != cls["name"]:
                    lines.append(f'{cls["name"]} --> {typ_clean} : {field["name"]}')
    return "\n".join(lines)

def main(src_dir, outfile="uml.puml"):
    java_files = find_java_files(src_dir)
    files_data = [parse_java_file(jf) for jf in java_files]
    pkgs = group_by_package(files_data)

    # Intro
    lines = [
        "@startuml",
        "skinparam linetype ortho",
        "skinparam style strictuml"
    ]

    # Packages, classes
    for i, (pkg, files) in enumerate(sorted(pkgs.items())):
        color = PKG_COLORS[i % len(PKG_COLORS)]
        lines.append(plantuml_package_block(pkg, files, color))

    # Héritages
    lines.append(plantuml_inheritance(files_data))
    # Associations
    lines.append(plantuml_associations(files_data))

    lines.append("@enduml")

    with open(outfile, "w", encoding="utf-8") as f:
        f.write("\n".join(lines))
    print(f"PlantUML diagram generated in {outfile}")

if __name__ == "__main__":
    import sys
    if len(sys.argv) < 2:
        print("Usage: python generate_uml.py <src_dir> [uml.puml]")
    else:
        src = sys.argv[1]
        out = sys.argv[2] if len(sys.argv) > 2 else "uml.puml"
        main(src, out)
