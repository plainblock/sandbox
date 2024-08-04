import argparse
from datetime import datetime


def load_args():
    # Setup parser
    parser = argparse.ArgumentParser(
        prog="generate",
        usage="python generate.py <prompt>",
        description="sample script.",
        epilog="end",
        add_help=True,
    )

    # Add arguments
    parser.add_argument("prompt", type=str, help="Prompt")
    parser.add_argument("-n", "--number", type=int, default=1, help="Number of images to generate")
    parser.add_argument("-t", "--token", type=str, default="token.txt", help="Token file")
    parser.add_argument("-m", "--model", type=str, default="gsdf/Counterfeit-V2.5", help="Model name")
    return parser.parse_args()


def load_token(filename="token.txt"):
    with open(filename) as f:
        line = f.readlines()
        return line[0]


def generate_images(model, prompt, negative_prompt="", number=1):
    for i in range(number):
        image = model(
            prompt=prompt,
            negative_prompt=negative_prompt,
        ).images[0]

        file_name = "generated-" + datetime.now().strftime("%Y%m%d%H%M%S") + ".png"
        image.save(file_name)
