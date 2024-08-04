import torch
from common import generate_images, load_args, load_token
from diffusers import StableDiffusion3Pipeline
from huggingface_hub import login


def setup_model(offload=True):
    model_name = "stabilityai/stable-diffusion-3-medium-diffusers"
    if torch.cuda.is_available():
        pipeline = StableDiffusion3Pipeline.from_pretrained(model_name, torch_dtype=torch.float16)
        if offload:
            pipeline.enable_model_cpu_offload()
        else:
            pipeline.to("cuda")

    else:
        pipeline = StableDiffusion3Pipeline.from_pretrained(model_name)

    return pipeline


if __name__ == '__main__':
    args = load_args()

    token = load_token(args.token)
    login(token=token)

    model = setup_model()
    generate_images(model, args.prompt)
