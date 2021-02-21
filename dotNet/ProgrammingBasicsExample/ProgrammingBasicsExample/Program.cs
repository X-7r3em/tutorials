using System;

namespace ProgrammingBasicsExample
{
    class Program
    {
        static void Main()
        {
            Console.Write("Enter integer number: ");
            int integerNumber = int.Parse(Console.ReadLine());

            Console.Write("Enter double number: ");
            double floatingPointNumber = double.Parse(Console.ReadLine());

            Console.Write("Enter character: ");
            char character = char.Parse(Console.ReadLine());

            Console.Write("Enter text: ");
            string textInput = Console.ReadLine();

            Console.WriteLine($"You have entered the number {integerNumber}");
            Console.WriteLine($"and {floatingPointNumber},");
            Console.WriteLine($"The character {character}");
            Console.WriteLine($"and text {textInput}");
            Console.WriteLine($"The floating point number {floatingPointNumber} rounded is {floatingPointNumber:F2}");
            Console.WriteLine("Formated double: {0:F3}", floatingPointNumber);
            Console.WriteLine($"Rounded up double: {Math.Ceiling(floatingPointNumber)}");
            Console.WriteLine($"Rounded down double: {Math.Floor(floatingPointNumber)}");
            Console.WriteLine($"Rounded double: {Math.Round(floatingPointNumber)}");
            Console.WriteLine($"Absolute value: {Math.Abs(integerNumber)}");
        }
    }
}
