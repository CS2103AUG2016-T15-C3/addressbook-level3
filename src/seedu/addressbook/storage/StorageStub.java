package seedu.addressbook.storage;

import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

public class StorageStub extends StorageFile {
	 /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }

        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }
    
    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {}

}
