# Stable Diffusion Sandbox

## Usage

1. Create virtual environment.

   ```shell
   python -m venv .env
   source .env/bin/activate
   ```

2. Install pakages.

   ```shell
   python -m pip install --upgrade pip
   pip install torch torchvision torchaudio diffusers sentencepiece protobuf peft transformers huggingface accelerate
   ```

3. Edit token file.

   cf. File must have only one line.

   ```shell
   vim token.txt
   ```

4. Edit prompt files.

   cf. Each files must have only one line.

   ```shell
   vim positive-prompt.txt
   vim negative-prompt.txt
   ```

5. Execute command.

   ```shell
   python main.py
   ```

### Command Options

| Short | Long          | Type | Description                  |
|:------|:--------------| :--- |:-----------------------------|
| `-n`  | `--number`    | int  | Number of images to generate |
| `-s`  | `--steps`     | int  | Number of steps              |
| `-m`  | `--model`     | str  | Model name                   |
| `-d`  | `--directory` | str  | Save directory               |

### Models

- "stabilityai/stable-diffusion-3-medium-diffusers"
- "gsdf/Counterfeit-V2.5"
