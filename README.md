# Delivery cost calculator

## Simple console application developed to calculate cost of delivery abstract good(s) of a particular weight to a particular distance.

<p>The application gets weight and distance parameters from the standard input and price parameters from a JSON-file.
The JSON-file considered to be placed at src/main/resources directory. The name of a JSON-file passes as a string argument to a main function.
If there was no arguments passed or the file with such name does not exist the program will use a prices.json file by default.
If all of input parameters are valid then the application will calculate delivery cost and will print the result into the standard output.</p>

<p>If any of the parameters is invalid then the application shall stop. The parameter becomes invalid in case it is a number less than 0 or it is not a number at all.</p>

<p>After successful cost calculating the application will suggest to enter new parameters to calculate new delivery cost. The application will behave the same until user enters an invalid parameter (this is the only way to stop the application).</p>
