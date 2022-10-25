# stringMixer
### A small application to generate a file with random unique strings. User can define minimal and maximal length of strings to generate. Also, the user can define how many strings the want to generate and characters to build strings with.
---

#### String generator

1. User defines minimal, maximal number of characters, quantity of strings and characters to use.
2. Calculate how many unique strings can be created from given charcters in given range.
3. Randomize quantity to table containing from shortest to longest numbers of strings.
4. Calculate maximum number of possible combinations in each length case.
5. For every number of quantity generate number 0, 1, and (next number ^ prime number)
6. If calculation is larger than maximal number; find next prime number, assing starting number to new prime number and calculate (new nex number ^ new prime number).
7. Number is than translated to charcters:
   - Number is translated from decimal to system number of character based.
   - Array of charcters is shuffled and than for number from 0 to max in system is assigned letter form array.
   - If number is shorter than given system, previous spaces are treated as zeros.
8. All the strings are saved to the file with name of a file creation timestamp.
9. The link to the file is added to the database.
