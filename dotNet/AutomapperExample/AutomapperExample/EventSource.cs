using System;
using System.Collections.Generic;
using System.Text;

namespace AutomapperExample
{
    class EventSource
    {
        public DateTime Date { get; set; }
        public string Name { get; set; }
    }

    class EventDestination
    {
        public string Event { get; set; }
        public string Name { get; set; }
    }
}
