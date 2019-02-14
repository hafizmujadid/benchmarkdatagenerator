package edu.tu.berlin.dima.benchmark.datagenerator;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonGenerator extends Thread {
    RateLimiter limiter;

    AtomicInteger seq;
    Producer<Long,String> personProducer;
    transient static final String[] cities = {
            "Abidjan","Abu","Acapulco","Aguascalientes","Akron","Albany",
            "Albuquerque","Alexandria","Allentown","Amarillo","Amsterdam",
            "Anchorage","Appleton","Aruba","Asheville","Athens","Atlanta", //
            "Augusta","Austin","Baltimore","Bamako","Bangor","Barbados",
            "Barcelona","Basel","Baton","Beaumont","Berlin","Bermuda","Billings",
            "Birmingham","Boise","Bologna","Boston","Bozeman","Brasilia",
            "Brunswick","Brussels","Bucharest","Budapest","Buffalo","Butte",
            "Cairo","Calgary","Cancun","Cape","Caracas","Casper","Cedar",
            "Charleston","Charlotte","Charlottesville","Chattanooga","Chicago",
            "Chihuahua","Cincinnati","Ciudad","Cleveland","Cody","Colorado",
            "Columbia","Columbus","Conakry","Copenhagen","Corpus","Cozumel",
            "Dakar","Dallas","Dayton","Daytona","Denver","Des","Detroit","Dothan",
            "Dubai","Dublin","Durango","Durban","Dusseldorf","East","El","Elko",
            "Evansville","Fairbanks","Fayetteville","Florence","Fort","Fortaleza",
            "Frankfurt","Fresno","Gainesville","Geneva","George","Glasgow",
            "Gothenburg","Grand","Great","Green","Greensboro","Greenville",
            "Grenada","Guadalajara","Guangzhou","Guatemala","Guaymas","Gulfport",
            "Gunnison","Hamburg","Harrisburg","Hartford","Helena","Hermosillo",
            "Honolulu","Houston","Huntington","Huntsville","Idaho","Indianapolis",
            "Istanbul","Jackson","Jacksonville","Johannesburg","Kahului",
            "Kalamazoo","Kalispell","Kansas","Key","Kiev","Killeen","Knoxville",
            "La","Lafayette","Lansing","Las","Lawton","Leon","Lexington","Lima",
            "Lisbon","Little","Lome","London","Long","Lorient","Los","Louisville",
            "Lubbock","Lynchburg","Lyon","Macon","Madison","Madrid","Manchester",
            "Mazatlan","Melbourne","Memphis","Merida","Meridian","Mexico","Miami",
            "Milan","Milwaukee","Minneapolis","Missoula","Mobile","Monroe",
            "Monterrey","Montgomery","Montreal","Moscow","Mulhouse","Mumbai",
            "Munich","Myrtle","Nagoya","Nashville","Nassau","New","Newark",
            "Newburgh","Newcastle","Nice","Norfolk","Oakland","Oklahoma","Omaha",
            "Ontario","Orange","Orlando","Ouagadougou","Palm","Panama","Paris",
            "Pasco","Pensacola","Philadelphia","Phoenix","Pittsburgh","Pocatello",
            "Port","Portland","Porto","Prague","Providence","Providenciales",
            "Puebla","Puerto","Raleigh","Rapid","Reno","Richmond","Rio","Roanoke",
            "Rochester","Rome","Sacramento","Salt","Salvador","San","Santiago",
            "Sao","Sarasota","Savannah","Seattle","Shannon","Shreveport","South",
            "Spokane","St","Stockholm","Stuttgart","Sun","Syracuse","Tallahassee",
            "Tampa","Tapachula","Texarkana","Tokyo","Toledo","Toronto","Torreon",
            "Toulouse","Tri","Tucson","Tulsa","Turin","Twin","Vail","Valdosta",
            "Vancouver","Venice","Veracruz","Vienna","Villahermosa","Warsaw",
            "Washington","West","White","Wichita","Wilkes","Wilmington",
            "Windhoek","Worcester","Zihuatenejo","Zurich"
    };

    private  int cityCount = cities.length;

    public PersonGenerator(AtomicInteger seq) {
        this.seq = seq;
        limiter = RateLimiter.create(Constants.INPUT_RATE);
        personProducer = KafkaSender.getPersonProducer("person");
    }

    @Override
    public void run() {
        //while (seq.get()<Constants.PERSON_RECORD_COUNT){
        while (true){
            if (limiter.tryAcquire()) {
                long id = seq.getAndIncrement();
                //200 bytes
                Person person = new Person(id, RandomStringUtils.randomAlphabetic(58),
                        RandomStringUtils.randomAlphabetic(40)+"@gmail.com",
                        RandomStringUtils.randomAlphabetic(20),
                        cities[RandomUtils.nextInt(0,cityCount)],
                        RandomStringUtils.randomAlphabetic(40),
                        System.currentTimeMillis());
                ProducerRecord<Long,String> record= new ProducerRecord<Long, String>(Constants.TOPIC_PERSON,
                        Thread.currentThread().getId(),person.toString());
                personProducer.send(record);
                System.out.println(id);
            }
        }
    }
}
