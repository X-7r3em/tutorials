using System;
using System.Linq;

namespace TechModuleExample
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] arr = Console.ReadLine().Split(" ").Select(int.Parse).ToArray();
            int rotationTimes = int.Parse(Console.ReadLine());


            rotationTimes = rotationTimes % arr.Length;

            int[] rotatedArr = new int[arr.Length];
            for (int i = 0; i < arr.Length; i++)
            {
                if (rotationTimes + i < arr.Length)
                {
                    rotatedArr[i] = arr[rotationTimes + i];
                }
                else
                {
                    rotatedArr[i] = arr[i + rotationTimes - arr.Length];
                }
            }

            Console.WriteLine(String.Join(" ", rotatedArr));
        }
    }
}
