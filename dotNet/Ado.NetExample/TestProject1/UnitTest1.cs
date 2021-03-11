using NUnit.Framework;
using System;
using System.Data.SqlClient;
using System.Transactions;

namespace TestProject1
{
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void Test1()
        {
            string connectionString = @"Server=.\SQLEXPRESS;Database=DemoDb;";

            // Provide the query string with a parameter placeholder.
            string queryString =
                "INSERT INTO people VALUES('Hello Test');";

            // Specify the parameter value.
            int paramValue = 5;

            //Create and open the connection in a using block.This
            //ensures that all resources will be closed and disposed
            // when the code exits.

            //using (TransactionScope trasactionScope = new TransactionScope())
            //{
            //    using (SqlConnection connection = new SqlConnection(connectionString))
            //    {
            //        // Create the Command and Parameter objects.
            //        SqlCommand command = new SqlCommand(queryString, connection);
            //        command.Parameters.AddWithValue("@pricePoint", paramValue);

            //        // Open the connection in a try/catch block.
            //        // Create and execute the DataReader, writing the result
            //        // set to the console window.
            //        try
            //        {
            //            connection.Open();
            //            command.ExecuteNonQuery();
            //            connection.Close();
            //            //connection.Open();
            //            //SqlDataReader reader = command.ExecuteReader();
            //            //while (reader.Read())
            //            //{
            //            //    Console.WriteLine("\t{0}\t{1}\t{2}",
            //            //        reader[0], reader[1], reader[2]);
            //            //}
            //            //reader.Close();
            //        }
            //        catch (Exception ex)
            //        {
            //            Console.WriteLine(ex.Message);
            //        }
            //        Console.ReadLine();
            //    }
            //}
        }
    }
}