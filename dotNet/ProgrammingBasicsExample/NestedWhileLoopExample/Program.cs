using System;

namespace NestedWhileLoopExample
{
    class Program
    {
        static void Main()
        {
            int nonPrimeCount = 0;
            int primeCount = 0;
            while (true)
            {
                string command = Console.ReadLine();
                if(command == "stop")
                {
                    break;
                }

                int number = int.Parse(command);
                if (number < 0)
                {
                    Console.WriteLine("Number is negative.");
                    continue;
                }

                bool isPrime = true;
                for (int i = 2; i <= Math.Sqrt(number); i++)
                {
                    if (number % i == 0)
                    {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime)
                {
                    primeCount += number;
                }
                else
                {
                    nonPrimeCount += number;
                }
            }

            Console.WriteLine($"Sum of all prime numbers is: {primeCount}");
            Console.WriteLine($"Sum of all non prime numbers is: {nonPrimeCount}");
        }
    }
}
