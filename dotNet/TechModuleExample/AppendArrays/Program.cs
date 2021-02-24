using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace AppendArrays
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> arrays = Console.ReadLine().Split("|").ToList();
            arrays.Reverse();
            var result = new List<string>();
            foreach (string arr in arrays)
            {
                List<string> split = arr.Split(" ").Where(s => !string.IsNullOrEmpty(s)).ToList();
                result.AddRange(split);
            }

            Console.WriteLine(string.Join(" ", result));
        }
    }
}
