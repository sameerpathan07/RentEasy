import { User } from "./user";

export interface Property {
  id: number;
  title: string;
  type: 'Apartment' | 'House' | 'Villa' | 'Studio';
  price: number;
  location: string;
  bedrooms: number;
  bathrooms: number;
  area: number; // sqft 
  imageUrl: string;
  description: string;
  available: boolean;
  user:User
  
}