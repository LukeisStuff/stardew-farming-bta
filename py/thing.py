from textwrap import dedent
from pathlib import Path

items: list[tuple[str, int]] = [
    ("grape_bottom", 6),
    ("grape_top", 3),
    ("corn_bottom", 7),
    ("corn_top", 4),
    ("beans_bottom", 7),
    ("beans_top", 3),
]

for name, stages in items:
    for stage in range(stages):
        file = Path(f"./model/block/crops/{name}/{stage}.json")
        content = dedent(f"""
            {{
                "parent": "minecraft:block/crops_wheat/template",

                "textures": {{
                    "crop": "stardew:block/crops_{name.split("_")[0]}/stage{stage}_{name.split("_")[1]}"
                }}
            }}
        """)

        file.parent.mkdir(exist_ok=True, parents=True)
        with file.open("w", encoding="utf-8") as writer:
            writer.write(content)
    #fi
#fi
