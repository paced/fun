"""Unit tests for the water overflow solver based on experimentation and algorithmic analysis."""

import math

from .. import overflow

# Define test constants.

TOLERANCE = 1e-5


def test_empty():
  """Test the contents of the top glass when no water is added."""
  
  expected_water = 0.0
  actual_water = overflow.determine_volume(0.0, 0, 0)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_top_fill():
  """
  Test a simple case where only the top glass is filled to a certain amount
  valid to 5 decimal places.
  """

  expected_water = 0.12345
  actual_water = overflow.determine_volume(0.12345, 0, 0)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_experimental_0():
  """Test a simple case with overflow happening."""

  expected_water = 0.0625
  actual_water = overflow.determine_volume(1.0, 2, 0)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_experimental_1():
  """Test a simple case with overflow happening."""

  expected_water = 0.125
  actual_water = overflow.determine_volume(1.0, 2, 1)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_experimental_2():
  """Test a simple case with overflow happening."""

  expected_water = 0.0625
  actual_water = overflow.determine_volume(1.0, 2, 2)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_experimental_3():
  """Test a simple case with overflow happening."""

  expected_water = 0.03125
  actual_water = overflow.determine_volume(2.0, 3, 0)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_experimental_4():
  """Test a simple case with overflow happening."""

  expected_water = 0.21875
  actual_water = overflow.determine_volume(2.0, 3, 1)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)


def test_experimental_5():
  """Test a simple case with overflow happening."""

  expected_water = 0.21875
  actual_water = overflow.determine_volume(2.0, 3, 2)

  assert math.isclose(expected_water, actual_water, rel_tol=TOLERANCE)
