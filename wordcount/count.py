"""Word counting problem solving (interview) script."""

from argparse import ArgumentParser, Namespace
from collections import Counter
from sys import exit

import re
import sys


def parse_cli(argv: list) -> Namespace:
    """Parse the CLI input from STDIN to retrieve an object that can be used to extract configuration items.

    :param argv: command line arguments from sys
    :returns: a Namespace that can extract configuration items defined from STDIN
    """

    argument_parser: ArgumentParser = ArgumentParser(
        description="Counts and sorts word frequencies")

    argument_parser.add_argument('file', metavar='file', type=str,
                                 help="the file to read for word counter.")
    argument_parser.add_argument('--stop-file', metavar='file', type=str,
                                 help="the file to read for words to exclude from the word frequency output.")
    argument_parser.add_argument('--output-file', metavar='file', type=str,
                                 help="if provided, the file to output to rather than STDOUT")
    argument_parser.add_argument('--min-count', metavar='count', type=int, default=0,
                                 help="the minimum number of occurences required before showing up in the output")

    return argument_parser.parse_args(argv)


def parse_word_frequencies(body: str) -> Counter:
    """Parse word frequencies in a text body in order to associate a word to a Counter.

    Note: Configurations (e.g.: stop file words and counts) are not applied at this step.

    :param body: the body of the text to parse
    :returns: a Counter from the collections module
    """

    words_in_order = [word.lower()
                      for word in re.findall(r'[A-Za-z]+[A-Za-z\-\']*', body) if word not in ('-', '\'')]

    return Counter(words_in_order)


def extract_stop_words(body: str) -> set:
    """Parse stop words in a text body as delimited by whitespace.

    :param body: the body of the text to parse
    :returns: a set of "stop-words"
    """

    return {word for word in re.findall(r'\w+', body)}


def try_open_handle_file(filename: str) -> str:
    """Try to open & read a file. Panic with a helpful error message if the file cannot be found or opened.

    :param filename: the filename of the file to open
    :returns: the body of the file, if successful
    """

    try:
        with(open(filename)) as f:
            return f.read()
    except FileNotFoundError:
        print(
            f'Fatal: The file `{filename}` does not exist and cannot be opened. Please check the filename!')

        exit(1)
    except IOError as error:
        print(
            f'Fatal: The file `{filename} cannot be opened! An exception was raised:\n\n', str(error))

        exit(1)


def main(argv: list):
    """Testable and callable main method for the counting application.

    :param argv: command line arguments from sys
    """

    args: Namespace = parse_cli(argv)

    # Store configuration items in main loop.

    file_to_process: str = args.file
    stop_file: str = args.stop_file
    min_count: int = args.min_count
    output_file: str = args.output_file

    # Open some files. At this point, `file_to_process` will give a non-None str value.

    file_to_process_body = try_open_handle_file(file_to_process)
    stop_file_body = try_open_handle_file(stop_file) if stop_file else ''

    # Process the input file and the stop word file, if configuration for it exists.

    word_counter: Counter = parse_word_frequencies(file_to_process_body)
    stop_words: set = extract_stop_words(stop_file_body)

    # Apply configurations, write to str, then write to a file descriptor.

    output = str()

    for word, frequency in word_counter.most_common():
        if frequency >= min_count and word not in stop_words:
            output += f'{word} : {frequency}\n'

    if output_file:
        with open(output_file, 'w') as f:
            f.write(output)
    else:
        print(output, end='')


if __name__ == "__main__":
    main(sys.argv[1:])
