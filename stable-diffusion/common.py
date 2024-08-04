import os
from datetime import datetime


def load_prompt(positive_file, negative_file):
    with open(positive_file) as f:
        positive = f.readlines()[0]

    with open(negative_file) as f:
        negative = f.readlines()[0]

    return positive, negative


def load_token(filename):
    with open(filename) as f:
        line = f.readlines()
        return line[0]


def generate_images(model, prompt, negative_prompt, number, save_dir):
    for i in range(number):
        image = model(
            prompt=prompt,
            negative_prompt=negative_prompt,
        ).images[0]

        file_name = save_dir + "/" + datetime.now().strftime("%Y%m%d%H%M%S") + ".png"
        image.save(file_name)
