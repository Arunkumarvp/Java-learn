import os

def parse_and_generate(input_file, output_file, title):
    with open(input_file, 'r') as f:
        lines = f.readlines()

    with open(output_file, 'w') as out:
        out.write(f"# {title}\n\n")
        out.write("Here are the implementations and expected outputs for this pattern set.\n\n")
        
        in_pattern = False
        pattern_comments = []
        pattern_code = []
        brace_count = 0
        
        for line in lines:
            stripped = line.strip()
            
            # Start of a pattern
            if stripped.startswith('// P') and ':' in stripped:
                if in_pattern:
                    # Flush previous if somehow not closed
                    pass
                in_pattern = True
                pattern_comments = [stripped]
                pattern_code = []
                brace_count = 0
                continue
                
            if in_pattern:
                if stripped.startswith('//'):
                    pattern_comments.append(stripped)
                else:
                    pattern_code.append(line)
                    if '{' in line:
                        brace_count += line.count('{')
                    if '}' in line:
                        brace_count -= line.count('}')
                    
                    # If we opened and closed the method
                    if brace_count == 0 and len(pattern_code) > 0 and '}' in stripped:
                        # Flush the pattern
                        title_line = pattern_comments[0].replace('//', '').strip()
                        out.write(f"### {title_line}\n\n")
                        
                        out.write("**Output shape:**\n")
                        out.write("```text\n")
                        for comment in pattern_comments[1:]:
                            out.write(comment.replace('// ', '').replace('//', '') + '\n')
                        out.write("```\n\n")
                        
                        out.write("**Java Code:**\n")
                        out.write("```java\n")
                        for code_line in pattern_code:
                            # Strip 4 spaces of indentation for cleaner display
                            if code_line.startswith('    '):
                                out.write(code_line[4:])
                            else:
                                out.write(code_line)
                        out.write("```\n\n---\n\n")
                        
                        in_pattern = False

files = [
    ('pattern/Patterns1to25.java', 'dsa-docs-site/markdown/patterns_1_25.md', 'Patterns 1 to 25'),
    ('pattern/Patterns26to50.java', 'dsa-docs-site/markdown/patterns_26_50.md', 'Patterns 26 to 50'),
    ('pattern/Patterns51to75.java', 'dsa-docs-site/markdown/patterns_51_75.md', 'Patterns 51 to 75'),
    ('pattern/Patterns76to100.java', 'dsa-docs-site/markdown/patterns_76_100.md', 'Patterns 76 to 100'),
]

for src, dest, title in files:
    if os.path.exists(src):
        parse_and_generate(src, dest, title)
        print(f"Generated {dest}")

