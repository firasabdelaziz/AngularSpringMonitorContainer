@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceControllerTest {

    @Mock
    private IInvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @Test
    public void testGetInvoices_ShouldReturnAllInvoices() throws Exception {
        List<Invoice> mockInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceService.retrieveAllInvoices()).thenReturn(mockInvoices);

        List<Invoice> retrievedInvoices = invoiceController.getInvoices();

        assertEquals(mockInvoices, retrievedInvoices);
        verify(invoiceService, times(1)).retrieveAllInvoices();
    }

    @Test
    public void testRetrieveInvoice_ShouldReturnInvoiceById() throws Exception {
        Long invoiceId = 1L;
        Invoice mockInvoice = new Invoice(invoiceId);
        when(invoiceService.retrieveInvoice(invoiceId)).thenReturn(mockInvoice);

        Invoice retrievedInvoice = invoiceController.retrieveInvoice(invoiceId);

        assertEquals(mockInvoice, retrievedInvoice);
        verify(invoiceService, times(1)).retrieveInvoice(invoiceId);
    }

    @Test
    public void testCancelInvoice_ShouldCallServiceMethod() throws Exception {
        Long invoiceId = 1L;

        invoiceController.cancelInvoice(invoiceId);

        verify(invoiceService, times(1)).cancelInvoice(invoiceId);
    }

    @Test
    public void testGetInvoicesBySupplier_ShouldReturnInvoicesBySupplierId() throws Exception {
        Long supplierId = 2L;
        List<Invoice> mockInvoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceService.getInvoicesBySupplier(supplierId)).thenReturn(mockInvoices);

        List<Invoice> retrievedInvoices = invoiceController.getInvoicesBySupplier(supplierId);

        assertEquals(mockInvoices, retrievedInvoices);
        verify(invoiceService, times(1)).getInvoicesBySupplier(supplierId);
    }

    @Test
    public void testAssignOperatorToInvoice_ShouldCallServiceMethod() throws Exception {
        Long idOperator = 3L;
        Long idInvoice = 4L;

        invoiceController.assignOperatorToInvoice(idOperator, idInvoice);

        verify(invoiceService, times(1)).assignOperatorToInvoice(idOperator, idInvoice);
    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates_ShouldCallServiceMethod() throws Exception {
        Date startDate = new Date(); // Simulate current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7); // Add 7 days to get endDate
        Date endDate = calendar.getTime();
        float expectedTotalAmount = 1000.0f;
        when(invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);

        float actualTotalAmount = invoiceController.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        assertEquals(expectedTotalAmount, actualTotalAmount, 0.001f); // Allow a small delta for floating-point comparison
    }
}
