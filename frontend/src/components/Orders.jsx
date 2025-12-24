import {
    Button,
    TextField,
    Typography,
    Stack,
    Card,
    CardContent,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
} from "@mui/material";
import {api} from "../api/api";
import {useState} from "react";
import {Alert} from "@mui/material";


const OrdersTable = ({orders}) => {
    if (!orders || orders.length === 0) return null;

    return (
        <Table size="small">
            <TableHead>
                <TableRow>
                    <TableCell>Order ID</TableCell>
                    <TableCell>User ID</TableCell>
                    <TableCell>Amount</TableCell>
                    <TableCell>Description</TableCell>
                    <TableCell>Status</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {orders.map((o) => (
                    <TableRow key={o.id}>
                        <TableCell>{o.id}</TableCell>
                        <TableCell>{o.userId}</TableCell>
                        <TableCell>{o.amount}</TableCell>
                        <TableCell>{o.description}</TableCell>
                        <TableCell>{o.status}</TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    );
};

const OrderStatusTable = ({order}) => {
    if (!order) return null;

    return (
        <Table size="small">
            <TableHead>
                <TableRow>
                    <TableCell>Order ID</TableCell>
                    <TableCell>Status</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                <TableRow>
                    <TableCell>{order.id}</TableCell>
                    <TableCell>{order.status}</TableCell>
                </TableRow>
            </TableBody>
        </Table>
    );
};


const CommandCard = ({children}) => (
    <Card
        sx={{
            backgroundColor: "background.default",
            border: "1px solid rgba(255,255,255,0.08)",
        }}
    >
        <CardContent>
            <Stack spacing={2}>
                {children}
            </Stack>
        </CardContent>
    </Card>
);


export default function Orders() {
    const [userId, setUserId] = useState("");
    const [amount, setAmount] = useState("");
    const [description, setDescription] = useState("");
    const [orderId, setOrderId] = useState("");

    const [createdOrder, setCreatedOrder] = useState(null);
    const [orderStatus, setOrderStatus] = useState(null);
    const [orders, setOrders] = useState([]);


    const [error, setError] = useState(null);

    const handleError = (e) => {
        const message =
            typeof e?.response?.data?.message === "string" ? e.response.data.message : "Unexpected error occurred";
        setError(message);
    };
    const createOrder = async () => {
        try {
            setError(null);
            const res = await api.post(`/order-service/order/${userId}/${amount}/${description}`);
            setCreatedOrder(res.data);
            await loadOrders();
        } catch (e) {
            handleError(e);
        }
    };

    const getOrderStatus = async () => {
        try {
            setError(null);
            const res = await api.get(`/order-service/${orderId}/status`);
            setOrderStatus({
                id: orderId,
                status: res.data,
            });
        } catch (e) {
            handleError(e);
        }
    };

    const loadOrders = async () => {
        try {
            setError(null);
            const res = await api.get(`/order-service/orders`);
            setOrders(res.data);
        } catch (e) {
            handleError(e);
        }
    };

    return (
        <Card>
            <CardContent>
                <Stack spacing={4}>
                    <Typography variant="h5" align="center" color="primary">
                        Order Service
                    </Typography>
                    {error && (
                        <Alert severity="error">
                            {error}
                        </Alert>
                    )}
                    <CommandCard>
                        <TextField
                            label="User ID"
                            fullWidth
                            onChange={(e) => setUserId(e.target.value)}
                        />

                        <TextField
                            label="Amount"
                            fullWidth
                            onChange={(e) => setAmount(e.target.value)}
                        />

                        <TextField
                            label="Description"
                            fullWidth
                            onChange={(e) => setDescription(e.target.value)}
                        />

                        <Button variant="contained" onClick={createOrder}>
                            Create order
                        </Button>

                        <OrdersTable
                            orders={createdOrder ? [createdOrder] : []}
                        />
                    </CommandCard>

                    <CommandCard>
                        <TextField
                            label="Order ID"
                            fullWidth
                            onChange={(e) => setOrderId(e.target.value)}
                        />

                        <Button variant="contained" onClick={getOrderStatus}>
                            Get order status
                        </Button>

                        <OrderStatusTable order={orderStatus}/>
                    </CommandCard>

                    <CommandCard>
                        <Button variant="contained" onClick={loadOrders}>
                            Reload all orders
                        </Button>

                        <OrdersTable orders={orders}/>
                    </CommandCard>
                </Stack>
            </CardContent>
        </Card>
    );
}
