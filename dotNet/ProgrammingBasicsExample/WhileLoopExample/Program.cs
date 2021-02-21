using System;

namespace WhileLoopExample
{
    class Program
    {
        static void Main()
        {
            double required = double.Parse(Console.ReadLine());
            double present = double.Parse(Console.ReadLine());
            int daysPassed = 0;
            int consecutiveSpendDays = 0;

            while (present < required)
            {
                daysPassed++;
                string command = Console.ReadLine().ToLower();
                double amount = double.Parse(Console.ReadLine());

                if (command == "save")
                {
                    present += amount;
                    consecutiveSpendDays = 0;
                }
                else if (command == "spend")
                {
                    present = Math.Max(present - amount, 0);
                    consecutiveSpendDays++;
                    if (consecutiveSpendDays == 5)
                    {
                        break;
                    }
                }
            }

            if (consecutiveSpendDays != 5)
            {
                Console.WriteLine($"You saved the money for {daysPassed} days.");
            }
            else
            {
                Console.WriteLine($"You can't save the money.");
                Console.WriteLine(daysPassed);
            }
        }
    }
}
