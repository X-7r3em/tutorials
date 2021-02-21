using System;

namespace IfElseExample
{
    class Program
    {
        static void Main()
        {
            Console.Write("Please enter a number: ");
            double number = double.Parse(Console.ReadLine());

            if (number > 5)
            {
                Console.WriteLine("The number is greater than 5");
            }
            else if (number < 5)
            {
                Console.WriteLine("The number is less than 5");
            }
            else
            {
                Console.WriteLine("The number is equal to 5");
            }

            Console.WriteLine("Placeholder");

            Console.WriteLine("This is the end of the program!");
        }
    }
}
