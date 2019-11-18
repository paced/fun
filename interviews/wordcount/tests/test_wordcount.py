"""Integration tests for the word counter script."""

import count


def test_nominal():
    """Test the nominal run file without any configuration."""

    count.main(['tests/files/in02.txt', "--output-file",
                "tests/output/output.txt"])

    with open("tests/files/out_nominal.txt") as expected, open("tests/output/output.txt") as actual:
        assert(expected.read() == actual.read())


def test_nominal_stop_words():
    """Test the nominal run file with stop words configuration."""

    count.main(['tests/files/in02.txt', "--output-file",
                "tests/output/output.txt", "--stop-file", "tests/files/stop.txt"])

    with open("tests/files/out_stop.txt") as expected, open("tests/output/output.txt") as actual:
        assert(expected.read() == actual.read())


def test_nominal_min_count():
    """Test the nominal run file with minimum count configuration."""

    count.main(['tests/files/in02.txt', "--output-file",
                "tests/output/output.txt", "--min-count", "5"])

    with open("tests/files/out_min.txt") as expected, open("tests/output/output.txt") as actual:
        assert(expected.read() == actual.read())


def test_nominal_full_config():
    """Test the nominal run file with minimum count configuration and stop words configuration."""

    count.main(['tests/files/in02.txt', "--output-file",
                "tests/output/output.txt", "--min-count", "5", "--stop-file", "tests/files/stop.txt"])

    with open("tests/files/out_full.txt") as expected, open("tests/output/output.txt") as actual:
        assert(expected.read() == actual.read())


def test_long_full_config():
    """Test the long file with full configuration."""

    count.main(['tests/files/in03.txt', "--output-file",
                "tests/output/output.txt", "--min-count", "5", "--stop-file", "tests/files/stop.txt"])

    with open("tests/files/out_long.txt") as expected, open("tests/output/output.txt") as actual:
        assert(expected.read() == actual.read())


def test_cross_platform_full_config():
    """Test the file with CRLF line endings with full configuration."""

    count.main(['tests/files/in01.txt', "--output-file",
                "tests/output/output.txt", "--min-count", "5", "--stop-file", "tests/files/stop.txt"])

    with open("tests/files/out_xplat.txt") as expected, open("tests/output/output.txt") as actual:
        assert(expected.read() == actual.read())
