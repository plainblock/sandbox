import argparse
import os
from common import generate_images, load_prompt, load_token
from model import setup_model
from huggingface_hub import login

if __name__ == '__main__':
    # Setup parser
    parser = argparse.ArgumentParser(
        prog="generate",
        usage="python generate.py",
        description="Generate image.",
        epilog="end",
        add_help=True,
    )

    # Add arguments
    parser.add_argument("-n", "--number", type=int, default=1, help="Number of images to generate")
    parser.add_argument("-m", "--model", type=str, default="gsdf/Counterfeit-V2.5", help="Model name")
    parser.add_argument("-s", "--steps", type=int, default=25, help="Number of steps")
    parser.add_argument("-d", "--directory", type=str, default="generate", help="Save directory")

    # View arguments
    args = parser.parse_args()
    print("model: {}".format(args.model))
    print("number: {}".format(args.number))
    print("steps: {}".format(args.steps))
    print("directory: {}".format(args.directory))

    # Read prompts
    positive, negative = load_prompt("positive-prompt.txt", "negative-prompt.txt")
    print("positive: {}".format(positive))
    print("negative: {}".format(negative))

    # Login to Hugging Face
    token = load_token("token.txt")
    login(token, add_to_git_credential=True)

    # Generate image
    os.makedirs(args.directory, exist_ok=True)
    model = setup_model(args.model)
    generate_images(model, positive, negative, args.number, args.steps, args.directory)
