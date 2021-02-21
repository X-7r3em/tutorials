using System;

namespace AdvancedIfElse
{
    class Program
    {
        static void Main()
        {
            int budget = int.Parse(Console.ReadLine());
            string season = Console.ReadLine().ToLower();
            int fisherMenCount = int.Parse(Console.ReadLine());
            double totalCost = 0;

            switch (season)
            {
                case "spring":
                    totalCost += 3000;
                    break;
                case "autumn":
                case "summer":
                    totalCost += 4200;
                    break;
                case "winter":
                    totalCost += 2600;
                    break;
            }

            if (fisherMenCount <= 6)
            {
                totalCost *= 0.9;
            }
            else if (fisherMenCount <= 11)
            {
                totalCost *= 0.85;
            }
            else
            {
                totalCost *= 0.75;
            }

            if (fisherMenCount % 2 == 0 && season != "autumn")
            {
                totalCost *= 0.95;
            }

            if (budget >= totalCost)
            {
                Console.WriteLine($"Yes! You have {budget - totalCost:F2} leva left.");
            }
            else
            {
                Console.WriteLine($"Not enough money! You need {totalCost - budget:F2} leva.");
            }
        }
    }
}
