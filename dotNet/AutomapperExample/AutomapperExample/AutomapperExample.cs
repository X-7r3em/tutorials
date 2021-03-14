using AutoMapper;
using NUnit.Framework;
using System;
using System.Reflection;

namespace AutomapperExample
{
    public class Example : Profile
    {
        /// <summary>
        /// Simple mapping
        /// </summary>
        [Test]
        public void SimpleMapping()
        {
            //Create a configuration and save the mapping
            MapperConfiguration mapperConfiguration =
                new MapperConfiguration(cfg => cfg.CreateMap<PersonSource, PersonDestination>());

            IMapper mapper = mapperConfiguration.CreateMapper();

            PersonSource source = new PersonSource("Vasil", 15, "Demo address", 170);

            PersonDestination destination = mapper.Map<PersonDestination>(source);

            Assert.AreEqual(source.Name, destination.Name);
            Assert.AreEqual(source.Age, destination.Age);
            Assert.AreEqual(source.Address, destination.Address);
            Assert.AreEqual(source.Height, destination.Height);
        }

        /// <summary>
        /// Custom mapping of a field
        /// </summary>
        [Test]
        public void CustomMapping()
        {
            //Creating a custom mapping
            MapperConfiguration mapperConfiguration =
                new MapperConfiguration(cfg =>
                cfg.CreateMap<EventSource, EventDestination>()
                    .ForMember(dest => dest.Event, opt => opt.MapFrom(src => src.Date.ToString("yyyy\\/MM\\/dd")))
                );

            IMapper mapper = mapperConfiguration.CreateMapper();

            EventSource source = new EventSource { Date = new DateTime(2000, 12, 30), Name = "Birthday" };

            EventDestination destination = mapper.Map<EventDestination>(source);

            Assert.AreEqual("2000/12/30", destination.Event);
            Assert.AreEqual("Birthday", destination.Name);
        }

        /// <summary>
        /// Custom mapping of a field using a profile for configuration
        /// </summary>
        [Test]
        public void CustomMappingWithProfile()
        {
            //Creating a custom mapping
            MapperConfiguration mapperConfiguration =
                new MapperConfiguration(
                    cfg => cfg.AddMaps(Assembly.GetExecutingAssembly())
                );

            IMapper mapper = mapperConfiguration.CreateMapper();

            EventSource source = new EventSource { Date = new DateTime(2000, 12, 30), Name = "Birthday" };

            EventDestination destination = mapper.Map<EventDestination>(source);

            Assert.AreEqual("2000/12/30", destination.Event);
            Assert.AreEqual("Birthday", destination.Name);
        }
    }
}