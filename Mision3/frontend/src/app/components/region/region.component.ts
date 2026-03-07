import { Component, OnInit } from '@angular/core';
import { Country } from '../../models/country';
import { CountryService } from '../../services/country.service';
import { Region } from '../../models/region';
import { RegionService } from '../../services/region.service';

@Component({
  selector: 'app-region',
  templateUrl: './region.component.html',
  styleUrl: './region.component.css'
})
export class RegionComponent implements OnInit{
  countries: Country[] = [];
   regions: Region[] = [];
  region: Region = new Region();

  constructor(private countryService: CountryService,
    private regionService: RegionService
  ) {}
   ngOnInit(): void {
    this.loadCountries();
    this.loadRegions();
  
  }

  loadCountries() {
    this.countryService.getCountries().subscribe(data => {
      this.countries = data;
    });
  }
   loadRegions() {
    this.regionService.getRegiones().subscribe(data => {
      this.regions = data;
    });
  }

  saveRegion() {
    this.regionService.createRegion(this.region).subscribe(data => {
      this.loadRegions();
      this.region = new Region();
    });
  }

}
