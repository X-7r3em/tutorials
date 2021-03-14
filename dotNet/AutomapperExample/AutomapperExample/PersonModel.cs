namespace AutomapperExample
{
    class PersonDestination
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public string Address { get; set; }
        public int Height { get; set; }

        public PersonDestination()
        {
        }

        public PersonDestination(string name, int age, string address, int height)
        {
            Name = name;
            Age = age;
            Address = address;
            Height = height;
        }
    }

    class PersonSource
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public string Address { get; set; }
        public int Height { get; set; }

        public PersonSource()
        {
        }

        public PersonSource(string name, int age, string address, int height)
        {
            Name = name;
            Age = age;
            Address = address;
            Height = height;
        }
    }
}
