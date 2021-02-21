using System;

namespace ForLoopExample
{
    class Program
    {
        static void Main()
        {
            int total = int.Parse(Console.ReadLine());
            int dividedByTwo = 0;
            int dividedByThree = 0;
            int dividedByFour = 0;

            for (int i = 0; i < total; i++)
            {
                int number = int.Parse(Console.ReadLine());
                if (number % 2 == 0)
                {
                    dividedByTwo++;
                }

                if (number % 3 == 0)
                {
                    dividedByThree++;
                }

                if (number % 4 == 0)
                {
                    dividedByFour++;
                }
            }

            Console.WriteLine($"{100.0 * dividedByTwo / total:F2}%");
            Console.WriteLine($"{100.0 * dividedByThree / total:F2}%");
            Console.WriteLine($"{100.0 * dividedByFour / total:F2}%");
        }
    }
}
