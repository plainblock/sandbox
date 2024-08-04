import os
import torch
from diffusers import DiffusionPipeline, StableDiffusionPipeline, StableDiffusion3Pipeline
from enum import Enum


class ModelType(Enum):
    SDV30 = "stabilityai/stable-diffusion-3-medium-diffusers"
    CFV25 = "gsdf/Counterfeit-V2.5"


def setup_sd3_model(offload=True):
    model_name = ModelType.SDV30.value
    if torch.cuda.is_available():
        pipeline = StableDiffusion3Pipeline.from_pretrained(model_name, torch_dtype=torch.float16)
        if offload:
            pipeline.enable_model_cpu_offload()
        else:
            pipeline.to("cuda")

    else:
        pipeline = StableDiffusion3Pipeline.from_pretrained(model_name)

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


def setup_safetensors_model(model_name, offload=True):
    if torch.cuda.is_available():
        pipeline = StableDiffusionPipeline.from_pretrained(model_name, torch_dtype=torch.float16)
        if offload:
            pipeline.enable_model_cpu_offload()
        else:
            pipeline.to("cuda")

    else:
        pipeline = StableDiffusionPipeline.from_pretrained(model_name)

    return pipeline


def setup_model(model_name, offload=True):
    if os.path.isfile(model_name):
        return setup_safetensors_model(model_name, offload)
    elif model_name == ModelType.SDV30.value:
        return setup_sd3_model(offload)
    else:
        return setup_general_model(model_name, offload)
