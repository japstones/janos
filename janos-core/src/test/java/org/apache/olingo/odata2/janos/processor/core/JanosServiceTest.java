package org.apache.olingo.odata2.janos.processor.core;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.janos.processor.api.JanosService;
import org.apache.olingo.odata2.janos.processor.core.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;


/**
 * Test the JanosService from the API package which use this implementation by default.
 *
 * Created by mibo on 17.10.15.
 */
public class JanosServiceTest {

  @Test
  public void createFromPackage() throws ODataException {
    ODataService service = JanosService.createFor(Building.class.getPackage().getName()).build();
    assertNotNull(service);
  }

  @Test
  public void createFromAnnotatedClasses() throws ODataException {
    final Collection<Class<?>> annotatedClasses = new ArrayList<>();
    annotatedClasses.add(RefBase.class);
    annotatedClasses.add(Building.class);
    annotatedClasses.add(Employee.class);
    annotatedClasses.add(Manager.class);
    annotatedClasses.add(Photo.class);
    annotatedClasses.add(Room.class);
    annotatedClasses.add(Team.class);
    ODataService service = JanosService.createFor(annotatedClasses).build();

    assertNotNull(service);
  }

  @Test(expected = ODataException.class)
  public void createFromClasses() throws ODataException {
    final Collection<Class<?>> notAnnotatedClasses = new ArrayList<>();
    notAnnotatedClasses.add(String.class);
    notAnnotatedClasses.add(Long.class);
    ODataService service = JanosService.createFor(notAnnotatedClasses).build();

    assertNotNull(service);
  }

  @Test
  public void createEdmFromPackage() throws ODataException {
    EdmProvider provider = JanosService.createEdmProvider(Building.class.getPackage().getName());

    assertNotNull(provider);
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Building")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Employee")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Manager")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Photo")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Room")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Team")));
    assertNotNull(provider.getComplexType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "c_City")));
  }

  @Test
  public void createEdmFromAnnotatedClasses() throws ODataException {
    final Collection<Class<?>> annotatedClasses = new ArrayList<>();
    annotatedClasses.add(RefBase.class);
    annotatedClasses.add(Building.class);
    annotatedClasses.add(Employee.class);
    annotatedClasses.add(Manager.class);
    annotatedClasses.add(Photo.class);
    annotatedClasses.add(Room.class);
    annotatedClasses.add(Team.class);
    annotatedClasses.add(City.class);

    EdmProvider provider = JanosService.createEdmProvider(annotatedClasses);

    assertNotNull(provider);
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Building")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Employee")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Manager")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Photo")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Room")));
    assertNotNull(provider.getEntityType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "Team")));
    assertNotNull(provider.getComplexType(new FullQualifiedName(ModelSharedConstants.NAMESPACE_1, "c_City")));
  }
}