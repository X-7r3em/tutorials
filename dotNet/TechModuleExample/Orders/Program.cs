using System;
using System.Collections.Generic;

namespace Orders
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary<string, double> pricesByProduct = new Dictionary<string, double>();
            Dictionary<string, int> amountByProduct = new Dictionary<string, int>();

            while (true)
            {
                string command = Console.ReadLine();
                if (command == "buy")
                {
                    break;
                }

                string[] tokens = command.Split(" ");
                string product = tokens[0];
                double price = double.Parse(tokens[1]);
                int amount = int.Parse(tokens[2]);

                pricesByProduct[product] = price;

                if (!amountByProduct.ContainsKey(product))
                {
                    amountByProduct[product] = 0;
                }

                amountByProduct[product] += amount;
            }

            foreach (var product in amountByProduct)
            {
                double average = product.Value * pricesByProduct[product.Key];
                Console.WriteLine($"{product.Key} -> {average:F2}");
            }

            var birthday = new { a = 1, b = 2, c = 3 };
        }
    }
}
