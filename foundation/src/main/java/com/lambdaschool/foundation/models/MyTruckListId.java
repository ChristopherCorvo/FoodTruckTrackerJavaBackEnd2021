package com.lambdaschool.foundation.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MyTruckListId implements Serializable
{
        /**
         * The id of the user
         */
        private long user;

        /**
         * The id of the truck
         */
        private long truck;

        /**
         * The default constructor required by JPA
         */
    public MyTruckListId()
    {
    }

    /**
         * Getter for the user id
         *
         * @return long the user id
         */
        public long getUser()
        {
            return user;
        }

        /**
         * Setter for the user id
         *
         * @param user the new user id for this object
         */
        public void setUser(long user)
        {
            this.user = user;
        }

        /**
         * Getter for the role id
         *
         * @return long the role id
         */
        public long getTruck()
        {
            return truck;
        }

        /**
         * The setter for the role id
         *
         * @param role the new role id for this object
         */
        public void setRole(long truck)
        {
            this.truck = truck;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            // boolean temp = (o.getClass() instanceof Class);
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            MyTruckListId that = (MyTruckListId) o;
            return user == that.user &&
                truck == that.truck;
        }

        @Override
        public int hashCode()
        {
            return 37;
        }
    }


