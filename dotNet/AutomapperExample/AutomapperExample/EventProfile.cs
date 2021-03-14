using AutoMapper;

namespace AutomapperExample
{
    public class EventProfile : Profile
    {
        public EventProfile()
        {
            CreateMap<EventSource, EventDestination>()
                .ForMember(
                    dest => dest.Event,
                    opt => opt.MapFrom(src => src.Date.ToString("yyyy\\/MM\\/dd"))
                );
        }
    }
}
