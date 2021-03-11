using System;
using System.Data;
using System.Data.SqlClient;

namespace Ado.NetExample
{
    class Program
    {
        private const string ConnectionString = @"Server=.\SQLEXPRESS;Database=DemoDb;Integrated Security=true";

        static void Main(string[] args)
        {

            //ExampleCreateTable();
            //ExampleInsertIntoDb();
            ExampleReadFromDb();
            ExampleUpdateDbWithDataSet();
        }

        static void ExampleInsertIntoDb()
        {
            // Provide the query string with a parameter placeholder.
            string queryString =
                "INSERT INTO people VALUES" +
                    "(@username)," +
                    "(@username)," +
                    "(@username);";

            // Specify the parameter value.
            string username = "UserName1";

            // Create and open the connection in a using block. This
            // ensures that all resources will be closed and disposed
            // when the code exits.
            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                // Create the Command and Parameter objects.
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Parameters.Add("@username", SqlDbType.NVarChar);
                command.Parameters["@username"].Value = username;

                // Open the connection in a try/catch block.
                // Create and execute the DataReader, writing the result
                // set to the console window.
                try
                {
                    connection.Open();
                    command.ExecuteNonQuery();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }

        static void ExampleCreateTable()
        {
            // Provide the query string with a parameter placeholder.
            string queryString =
                "DROP TABLE IF EXISTS people;" +
                "DROP TABLE IF EXISTS cars;" +
                "CREATE TABLE cars (" +
                    "id INT PRIMARY KEY IDENTITY (1,1)," +
                    "make NVARCHAR(50)," +
                    "model NVARCHAR(50)" +
                ");" +
                "CREATE TABLE people (" +
                    "id INT PRIMARY KEY IDENTITY (1,1)," +
                    "username NVARCHAR(50)," +
                ");";


            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);

                try
                {
                    connection.Open();
                    command.ExecuteNonQuery();
                    connection.Close();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }

        /// <summary>
        /// The SqlDataReader is a one-way reader.
        /// </summary>
        static void ExampleReadFromDb()
        {
            string queryString =
                "SELECT * FROM people WHERE username != @username;";

            string username = "Gosho";

            using (SqlConnection connection = new SqlConnection(ConnectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Parameters.AddWithValue("@username", username);

                try
                {
                    connection.Open();
                    SqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        Console.WriteLine("--------------------");
                        Console.WriteLine("By column index: ");
                        Console.WriteLine($"Id: {reader[0]}");
                        Console.WriteLine($"Username: {reader[1]}");
                        Console.WriteLine("By column name: ");
                        Console.WriteLine($"Id: {reader["id"]}");
                        Console.WriteLine($"Username: {reader["username"]}");
                    }
                    reader.Close();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
        }


        /// <summary>
        /// A DataSet can be used to read the information from the database and update it at the same time
        /// </summary>
        static void ExampleUpdateDbWithDataSet()
        {
            string connectionString = ConnectionString;

            using (SqlConnection connection = new SqlConnection(connectionString))
            {

                try
                {
                    connection.Open();
                    DataSet orderDataSet = new DataSet("people");

                    DataTable orderTable = orderDataSet.Tables.Add("orders");
                    SqlDataAdapter dataAdapter = new SqlDataAdapter();
                    //DataColumn orderPrimaryKey = orderTable.Columns.Add("Id", typeof(Int32));
                    //orderTable.Columns.Add("OrderQuantity", typeof(Int32));
                    //orderTable.Columns.Add("CompanyName", typeof(string));

                    //orderTable.PrimaryKey = new DataColumn[] { orderPrimaryKey };

                    DataRow row = orderTable.NewRow();
                    row[1] = "test";
           
                    dataAdapter.Update(orderTable);
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
            }
         

        }
    }
}
