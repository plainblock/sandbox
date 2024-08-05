import os
from datetime import datetime


def load_prompt(positive_file, negative_file):
    with open(positive_file) as f:
        line = f.readline()
        if len(line) == 0:
            positive = "a professional photograph of an astronaut riding a horse"
        else:
            positive = line

    with open(negative_file) as f:
        line = f.readline()
        if len(line) == 0:
            negative = ""
        else:
            negative = line

    return positive, negative


def load_token(filename):
    with open(filename) as f:
        line = f.readline()
        return line


def generate_images(model, prompt, negative_prompt, number, save_dir):
    for i in range(number):
        image = model(
            prompt=prompt,
            negative_prompt=negative_prompt,
        ).images[0]

        file_name = save_dir + "/" + datetime.now().strftime("%Y%m%d%H%M%S") + ".png"
        image.save(file_name)
