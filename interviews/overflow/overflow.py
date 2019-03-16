"""Calculates the "Water Overflow" problem."""

import argparse

# Problem constants.

GLASS_CAPACITY = 0.25
INITIAL_GLASS_VOLUME = 0.0


def determine_volume(litres, row, column):
  """
  Given some water poured into the top of a glass pyramid and a glass' row and 
  column index, return that glass' volume.
  """

  # Protect against problematic inputs.

  if column > row:
    raise IndexError("That glass does not exist in the pyramid!")

  # Initialise.

  required_glasses = litres // GLASS_CAPACITY
  required_rows = __compute_triangle_number(required_glasses)

  glasses = __init_glasses_array(required_rows)

  # Fill the glasses.

  glasses[0][0] = litres

  for i, _ in enumerate(glasses):
    for j, _ in enumerate(glasses[i]):
      # Note: glass is not a pointer to the float value, but a copy.

      glass = glasses[i][j]

      if glass > GLASS_CAPACITY:
        overflow = glass - GLASS_CAPACITY

        glasses[i + 1][j] += overflow / 2.0
        glasses[i + 1][j + 1] += overflow / 2.0

        glasses[i][j] = GLASS_CAPACITY

  return glasses[row][column]


def __compute_triangle_number(prior_number):
  """
  Determine the next triangle number from the given number or the given number
  if it is a triangle number.
  """

  number = -1
  n = 1

  while number < prior_number:
    number = n * (n + 1) /2
    n += 1

  return int(number)


def __init_glasses_array(required_rows):
  """
  Initialise an array of glasses with 'row index + 1' number of glasses for 
  each row.
  """

  glasses_array = list()

  for i in range(required_rows):
    glasses_row = list()

    for j in range(i + 1):
      glasses_row.append(INITIAL_GLASS_VOLUME)

    glasses_array.append(glasses_row)

  return glasses_array


if __name__ == "__main__":
  parser = argparse.ArgumentParser(
    description='Solve the Water Overflow algorithmic problem.'
  )
  
  parser.add_argument('litres',
    metavar='N',
    type=float,
    help='the amount of litres to add'
  )
  parser.add_argument('row', metavar='i', type=int, help='row index to query')
  parser.add_argument('column',
    metavar='j',
    type=int,
    help='column index to query'
  )

  args = parser.parse_args()

  print(determine_volume(args.litres, args.row, args.column))
