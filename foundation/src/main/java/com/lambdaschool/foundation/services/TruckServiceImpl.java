package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.MenuItem;
import com.lambdaschool.foundation.models.MenuItemPhoto;
import com.lambdaschool.foundation.models.MenuItemRating;
import com.lambdaschool.foundation.models.Truck;
import com.lambdaschool.foundation.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "truckservice")
public class TruckServiceImpl implements TruckService
{
    // connects the repository to the service
    @Autowired
    private TruckRepository truckrepository;

    // I will use this in my endpoint to return all trucks
    @Override
    public List<Truck> findAll()
    {
        List<Truck> truckList = new ArrayList<>();
        truckrepository.findAll()
            .iterator()
            .forEachRemaining(truckList::add);
        return truckList;
    }

    // find a Truck object by Id
    @Override
    public Truck findTruckById(long id)
    {
        return truckrepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Truck id " + id + " not found!"));
    }

    // find a Truck object by Name


    @Override
    public Truck findTruckByName(String name)
    {
        Truck truck = truckrepository.findByName(name);

        if(truck == null)
        {
            throw new ResourceNotFoundException("Truck " + name + " not found!");
        }

        return truck;
    }

    @Override
    public List<Truck> findTruckByNameLike(String name)
    {
        List<Truck> truckList = truckrepository.findByNameContainingIgnoreCase(name);
        return truckList;
    }

    @Override
    public List<Truck> findByCuisineType(String name)
    {
        List<Truck> truckList = truckrepository.findByCuisineType(name);
        return truckList;
    }

    // I will use this for my POST endpoint to add a new Truck obj to the database
    // Why create a new truck object when you can do an id check of the original truck object
    @Transactional
    @Override
    public Truck save(Truck truck)
    {
        Truck newTruck = new Truck();

        if(truck.getTruckid() !=0)
        {
            truckrepository.findById(truck.getTruckid())
                .orElseThrow(() -> new ResourceNotFoundException("Truck Id " + truck.getTruckid() + " not found!"));
            newTruck.setTruckid(truck.getTruckid());
        }

        newTruck.setName(truck.getName());
        newTruck.setImageOfTruck(truck.getImageOfTruck());
        newTruck.setCuisineType(truck.getCuisineType());
        newTruck.setTruckLongitude(truck.getTruckLongitude());
        newTruck.setTruckLatitude(truck.getTruckLatitude());
        newTruck.setDepartureTime(truck.getDepartureTime());
        newTruck.setCustomerRatingAvg(truck.getCustomerRatingAvg());


        // this is a data verification for the menuItems
        newTruck.getMenuItems()
            .clear();
        for (MenuItem m : truck.getMenuItems()) // going through list of menu items, have to go through its list of urls and add them to the
        {
            MenuItem m1 = new MenuItem(m.getItemName(), m.getItemDescription(),m.getItemPrice(),m.getCustomerRatingAvg(), newTruck);
            for (MenuItemPhoto mPhoto : m.getMenuItemPhotos())
            {
                m1.getMenuItemPhotos().add(new MenuItemPhoto(mPhoto.getPhotoURL(),m1));
            }

            // for loop to graft on the menuItemRatings
            for (MenuItemRating mRating : m.getMenuitemratings())
            {
                m1.getMenuitemratings().add(new MenuItemRating(mRating.getItemRating(),m1));
            }

            newTruck.getMenuItems()
                .add(m1);
        }


        return truckrepository.save(newTruck); // this should be newTruck
    }

    @Transactional
    @Override
    public Truck update(
        Truck truck,
        long id)
    {
        Truck currentTruck = truckrepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Truck " + id + " not found"));

        if (truck.getName() !=null)
        {
            currentTruck.setName(truck.getName());
        }

        if (truck.getImageOfTruck() !=null)
        {
            currentTruck.setImageOfTruck(truck.getImageOfTruck());
        }

        if(truck.getCuisineType() !=null)
        {
            currentTruck.setCuisineType(truck.getCuisineType());
        }

//        if(truck.getCustomerRatingAvg() !=null)
//        {
//            currentTruck.setCustomerRatingAvg(truck.getCustomerRatingAvg());
//        }

        if (truck.getDepartureTime() !=null)
        {
            currentTruck.setDepartureTime(truck.getDepartureTime());
        }

        if(truck.getTruckLongitude() !=null)
        {
            currentTruck.setTruckLongitude((truck.getTruckLongitude()));
        }

        return truckrepository.save(currentTruck);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if(truckrepository.findById(id)
            .isPresent())
        {
            truckrepository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException("Truck id " + id + " not found!");
        }
    }

    @Override
    public void deleteAllTrucks()
    {
        truckrepository.deleteAll();
    }
}
