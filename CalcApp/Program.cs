using CalcApp;

Console.WriteLine("Enter the first number:");
var a = Convert.ToDouble(Console.ReadLine());

Console.WriteLine("Enter the operation sign:");
var op = Convert.ToString(Console.ReadLine());

Console.WriteLine("Enter the second number:");
var b = Convert.ToDouble(Console.ReadLine());

var calculator = new Calculator();
var result = calculator.Calc(a, b, op);

Console.WriteLine("Result: " + result);
