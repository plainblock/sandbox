import torch
from common import generate_images, load_args, load_token
from diffusers import DiffusionPipeline
from huggingface_hub import login


def setup_model(model_name, offload=True):
    if torch.cuda.is_available():
        pipeline = DiffusionPipeline.from_pretrained(model_name, torch_dtype=torch.float16)
        if offload:
            pipeline.enable_model_cpu_offload()
        else:
            pipeline.to("cuda")

    else:
        pipeline = DiffusionPipeline.from_pretrained(model_name)

    return pipeline


if __name__ == '__main__':
    args = load_args()

    token = load_token(args.token)
    login(token=token)

    model = setup_model(args.model)
    generate_images(model, args.prompt)
