using System;
using System.Text;

namespace CaesarCipher
{
    class Program
    {
        static void Main(string[] args)
        {
            string decryptedMessage = Console.ReadLine();

            StringBuilder encryptedMessage = new StringBuilder();

            for (int i = 0; i < decryptedMessage.Length; i++)
            {
                encryptedMessage.Append((char)(decryptedMessage[i] + 3));
            }

            Console.WriteLine(encryptedMessage);
        }
    }
}
