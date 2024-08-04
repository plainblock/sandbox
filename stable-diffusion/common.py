from datetime import datetime


def load_prompt(filename):
    with open(filename) as f:
        line = f.readlines()
        return line[0], line[1]


def load_token(filename):
    with open(filename) as f:
        line = f.readlines()
        return line[0]


def generate_images(model, prompt, negative_prompt, number):
    for i in range(number):
        image = model(
            prompt=prompt,
            negative_prompt=negative_prompt,
        ).images[0]

        file_name = "generated-" + datetime.now().strftime("%Y%m%d%H%M%S") + ".png"
        image.save(file_name)
