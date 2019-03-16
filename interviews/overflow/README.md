# overflowProblem

An algorithmic/programming challenge.

## Problem

> The following is a paraphrase of the problem found in the specification.

Given a pyramid of 250 mL glasses stacked on top of each other with each row of glasses containing 1 more glass than the "row" above it, when N litres of water is poured on the top-most glass, how much water is in any glass i rows from the top and j glasses from the left most glass of that column?

### Inputs

> "infinity" used here is an arbitrarily large number, not the concept of infinity. Such a number is limited by the hardware of the server on which the module is run. The same applies for the remainder of this document.

- Decimal number of litres of water poured in the top-most glass from 0.0 to infinity.
- Integer row of any glass from 0 to infinity.
- Integer column of any glass from 0 to infinity.

Note that algorithmic inputs are different from user/other inputs.

### Outputs

- Decimal number of litres of water in the queried glass from 0.0 L to 0.250 L.

Note that algorithmic outputs are different from user/other outputs.

### Design

#### Architecture

A Python module will be used to perform the calculation of each glass allowing abstraction between the serving layer (in this case, only a "main" method).

### Algorithm

#### Definition

The glasses are stacked as thus:

```markdown
0: g
1: g g
2: g g g
3: g g g g
```

...and so on. The top `g` will flow down water to the g right below it and the one to the right. The rate of filling of any glass is dependant on the rate of filling from the glasses above. This, according to experimentation with inputs, appears to be an incidence of Pascal's Triangle. `We can use this for our unit tests.`

Because it must be known how much water is in the row directly above any row to determine the value in the bottom most row, we must keep a data structure containing all of the glasses.

#### Pseudocode

> This is formatted as Python but is not valid code.

Assume L is the number of litres poured into the top glass.

```python
CAPACITY = 0.250

required_glasses = L / capacity
required_rows = __compute_triangle_number(required_glasses)
glass_array = __initialise_array(required_rows)

# Fill the top glass with all of the water.

glass_array[0][0] = L

for row in glass_array:
  for glass in row:
    if glass > CAPACITY:
      flow = glass_array(glass - CAPACITY) / 2

      row[glass.index] += flow
      row[glass.index + 1] += flow

# The return value should be glass_array[i][j].
```

#### Problem Assumptions

- Assume that the water takes no time to flow.
- Assume that water evaporation, spillage, and other real-world physical factors aside from gravity do not play a part in the amount of water in any cup.
- Assume that for the amount of water poured in the top glass the input will be interpreted up to 5 decimal places.
- Assume that for the output amount of water will be within 1e-5 of the actual amount of water in that glass.

#### Testing

##### Algorithm and Module Unit Testing

We are able to easily determine the amount of water in any glass given a predetermined amount of water poured in the top glass. This may be done for elementary cases up to a certain amount of water.

For unit tests to pass, the output result must be within 1e-5 of the actual expected amount of water.

More information on running the tests can be found below in the [Build](#build) section.

##### CLI Acceptance Testing

There is little point in testing the CLI as the `argparse` standard library is well tested, i.e.: there is little value in these tests.

#### Exceptions

For illegal inputs (in this case, asking for a column that does not exist) the Python module will raise an exception.

## Build

### Prerequisites

The preferred way of building this application will be shown first, then with more "conventional" build instructions:

- pipenv >= 2018.7.1
- python >= 2.7 or >= 3.5

```bash
pip install pipenv
```

The conventiona requirements are (without the build tool):

- python 3.5
- pytest

```bash
pip install pytest
```

### Build Instructions

Pipenv is a similar build tool to `rpm` that bundles local dependencies, manages versions of Python, and can execute scripts in a local Python environment.

Install all dependencies with:

```bash
pipenv install
```

### Running Instructions

The interface to this problem's solution is a command-line interface. To execute:

```bash
pipenv run python overflow.py <litres> <row> <column>
```

For example:

```bash
pipenv run python overflow.py 1.0 2 1
```

Without pipenv, you may simply run:

```bash
python overflow.py 1.0 2 1
```

### Running Tests

You can run the 8 unit tests by running:

```bash
pipenv run pytest
```

If you don't use pipenv and have pytest, run:

```bash
pytest
```
