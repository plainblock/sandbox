import torch
from diffusers import DiffusionPipeline, StableDiffusion3Pipeline
from enum import StrEnum


class ModelType(StrEnum):
    SDV30 = "stabilityai/stable-diffusion-3-medium-diffusers"
    CFV25 = "gsdf/Counterfeit-V2.5"


def setup_sd3_model(offload=True):
    if torch.cuda.is_available():
        pipeline = StableDiffusion3Pipeline.from_pretrained(ModelType.SDV30, torch_dtype=torch.float16)
        if offload:
            pipeline.enable_model_cpu_offload()
        else:
            pipeline.to("cuda")

    else:
        pipeline = StableDiffusion3Pipeline.from_pretrained(ModelType.SDV30)

    return pipeline


def setup_general_model(model_name, offload=True):
    if torch.cuda.is_available():
        pipeline = DiffusionPipeline.from_pretrained(model_name, torch_dtype=torch.float16)
        if offload:
            pipeline.enable_model_cpu_offload()
        else:
            pipeline.to("cuda")

    else:
        pipeline = DiffusionPipeline.from_pretrained(model_name)

    return pipeline


def setup_model(model_type, offload=True):
    if model_type == ModelType.SDV30:
        return setup_sd3_model(offload)
    else:
        return setup_general_model(model_type, offload)
