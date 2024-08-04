import argparse
from common import generate_images, load_prompt, load_token
from model import setup_model
from huggingface_hub import login

DEFAULT_NUMBER = 1
DEFAULT_MODEL = "stabilityai/stable-diffusion-3-medium-diffusers"
DEFAULT_PROMPT = "prompt.txt"
DEFAULT_TOKEN = "token.txt"

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
    parser.add_argument("-n", "--number", type=int, default=DEFAULT_NUMBER, help="Number of images to generate")
    parser.add_argument("-m", "--model", type=str, default=DEFAULT_MODEL, help="Model name")
    parser.add_argument("-p" "--prompt", type=str, default=DEFAULT_PROMPT, help="Prompt file, must 2 lines")
    parser.add_argument("-t", "--token", type=str, default=DEFAULT_TOKEN, help="Token file, must 1 line")

    # Load settings
    args = parser.parse_args()
    positive, negative = load_prompt(args.prompt)
    token = load_token(args.token)
    login(token, add_to_git_credential=True)

    # Generate image
    model = setup_model(args.model)
    generate_images(model, positive, negative, args.number)
