package org.launchcode.models.data;

import javafx.geometry.Pos;
import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
public class JobData {

    private ArrayList<Job> jobs = new ArrayList<>();
    private static JobData instance;

    private JobFieldData<Employer> employers = new JobFieldData<>();
    private JobFieldData<Location> locations = new JobFieldData<>();
    private JobFieldData<CoreCompetency> coreCompetencies = new JobFieldData<>();
    private JobFieldData<PositionType> positionTypes = new JobFieldData<>();


    private JobData() {
        JobDataImporter.loadData(this);
    }


    public static JobData getInstance() {
        if (instance == null) {
            instance = new JobData();
        }
        return instance;
    }

    public Job findById(int id) {
        for (Job job : jobs) {
            if (job.getId() == id)
                return job;
        }

        return null;
    }

    public ArrayList<Job> findAll() {
        return jobs;
    }


    public ArrayList<Job> findByColumnAndValue(JobFieldType column, String value) {

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {
            if (getFieldByType(job, column).contains(value))
                matchingJobs.add(job);
        }

        return matchingJobs;
    }


    public ArrayList<Job> findByValue(String value) {

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {

            if (job.getName().toLowerCase().contains(value)) {
                matchingJobs.add(job);
                continue;
            }

            for (JobFieldType column : JobFieldType.values()) {
                if (column != JobFieldType.ALL && getFieldByType(job, column).contains(value)) {
                    matchingJobs.add(job);
                    break;
                }
            }
        }

        return matchingJobs;
    }

//    //locate the pre-existing objects for the fields passed in jobForm
//    Employer employer = jobData.getEmployers().findById(jobForm.getEmployerId());
//    Location location = jobData.getLocations().findById(jobForm.getLocationId());
//    PositionType positionType = jobData.getPositionTypes().findById(jobForm.getPositionTypeId());
//    CoreCompetency coreCompetency = jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId());
//
//    //public Job(String aName, Employer aEmployer, Location aLocation, PositionType aPositionType, CoreCompetency aSkill)
//    Job newJob = new Job(jobForm.getName(), employer, location, positionType, coreCompetency);
//        jobData.add(newJob);

    public int addJobListing(JobForm jobForm){
        Job newJob = new Job(jobForm.getName(),
            this.getEmployers().findById(jobForm.getEmployerId()),
            this.getLocations().findById(jobForm.getLocationId()),
            this.getPositionTypes().findById(jobForm.getPositionTypeId()),
            this.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
        this.add(newJob);
        return newJob.getId();

    }
    public int addJobListing(String jobName, int employerID, int locationID, int positionTypeID, int coreCompetencyID){
        Job newJob = new Job(jobName,
            this.getEmployers().findById(employerID),
            this.getLocations().findById(locationID),
            this.getPositionTypes().findById(positionTypeID),
            this.getCoreCompetencies().findById(coreCompetencyID));
        this.add(newJob);
        return newJob.getId();

    }

    private void add(Job job) {
        jobs.add(job);
    }


    private static JobField getFieldByType(Job job, JobFieldType type) {
        switch(type) {
            case EMPLOYER:
                return job.getEmployer();
            case LOCATION:
                return job.getLocation();
            case CORE_COMPETENCY:
                return job.getCoreCompetency();
            case POSITION_TYPE:
                return job.getPositionType();
        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }

    public JobFieldData<Employer> getEmployers() {
        return employers;
    }

    public JobFieldData<Location> getLocations() {
        return locations;
    }

    public JobFieldData<CoreCompetency> getCoreCompetencies() {
        return coreCompetencies;
    }

    public JobFieldData<PositionType> getPositionTypes() {
        return positionTypes;
    }
}
