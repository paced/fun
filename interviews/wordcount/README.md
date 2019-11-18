# Word Counting Script

A simple Python script that counts the words in a file with some neat additional features.

## Strategy

Because the script is simple, I wrote this script to show off my knowledge of the more "cutting edge" Python 3 features and good Pythonic practices (especially with docstrings). I focused on correctness, testing of edge cases, and use as an actual product that could be distributed as a script, i.e.: "production-ready" code.

## Build Requirements

This script was built with no non-`stdlib` dependencies (except for testing and linting) but a Pipfile is included to standardise deployments. As seen in that file, Python >= 3.7.5 is required to run this script.

Simply run `python3 count.py` depending on your operating system.

For all operating systems, after installing `pipenv` via `pip install pipenv`, run:

```sh
pipenv install
pipenv run count.py -h
```

...to run the script and display a help message.

```sh
pipenv run pytest
pipenv run mypy count.py
```

...to run the test suite and check mypy type annotations/linting.

## Features

- Minimum count of words feature is included.
- "Stop files" feature is included.
- This passes the MyPy lint using annotations. This gives Python a degree of "free" type safety.
- Every kind of input is handled gracefully (debugging information is specific to the input problems).
- Several inputs are tested using Pytest.

## Inputs

- The input text file can be any file.
- The stopfile expects whitespace-delimited words (either on a line split by spaces/tabs and/or on multiple lines).
- See command line input documentation with the command in [Build Requirements](#build-requirements). In short, try:

```sh
pipenv run count.py input.txt --output-to-file --min-count 5 --stop-file stopfile.txt
```

## Notes

- The assumption that the latest version of Python 3.7 sorts its dicts by key is ignored for this to ensure cross-platform (and some cross-version) support. An extra step is taken to ensure sortedness and this is deliberate.
- An assumption is made that a word like "doesn't" is considered a single word rather than a contraction of "does" and "not". Similarly, compound words are supported with a hyphen (e.g.: stop-word).
