from textwrap import dedent
from pathlib import Path

def content(stage, name):
    return dedent(f"""
        {{
            "parent": "minecraft:block/crops_pumpkin/stage{stage}",

            "textures": {{
                "side": "stardew:block/crops_{name}/stage{stage}_side.png",
                "top": "stardew:block/crops_{name}/stage{stage}_top.png"
            }}
        }}
    """)
#fi

for name in ["cauliflower", "watermelon"]:
    for stage in range(1, 5):
        file = Path(f"./models/block/crops/{name}/{stage-1}.json")
        file.parent.mkdir(parents=True, exist_ok=True)
        file.open("w", encoding="utf-8").write(content(stage, name))
