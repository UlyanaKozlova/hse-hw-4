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

const AccountTable = ({accounts}) => {
    if (!accounts || accounts.length === 0) return null;

    return (
        <Table size="small">
            <TableHead>
                <TableRow>
                    <TableCell>Account ID</TableCell>
                    <TableCell>User ID</TableCell>
                    <TableCell>Balance</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {accounts.map((acc) => (
                    <TableRow key={acc.id}>
                        <TableCell>{acc.id}</TableCell>
                        <TableCell>{acc.userId}</TableCell>
                        <TableCell>{acc.balance}</TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    );
};

const BalanceTable = ({account}) => {
    if (!account) return null;

    return (
        <Table size="small">
            <TableHead>
                <TableRow>
                    <TableCell>Account ID</TableCell>
                    <TableCell>Balance</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                <TableRow>
                    <TableCell>{account.id}</TableCell>
                    <TableCell>{account.balance}</TableCell>
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


export default function Accounts() {
    const [userId, setUserId] = useState("");
    const [accountId, setAccountId] = useState("");
    const [sum, setSum] = useState("");

    const [createdAccount, setCreatedAccount] = useState(null);
    const [topUpAccount, setTopUpAccount] = useState(null);
    const [balanceAccount, setBalanceAccount] = useState(null);
    const [accounts, setAccounts] = useState([]);
    const [error, setError] = useState(null);
    const handleError = (e) => {
        const message =
            typeof e?.response?.data?.message === "string" ? e.response.data.message : "Unexpected error";
        setError(message);
    };
    const createAccount = async () => {
        try {
            setError(null);
            const res = await api.post(`/payment-service/${userId}/account`);
            setCreatedAccount(res.data);
            await loadAccounts();
        } catch (e) {
            handleError(e);
        }
    };

    const topUp = async () => {
        try {
            setError(null);
            const res = await api.patch(`/payment-service/${accountId}/top-up/${sum}/account`);
            setTopUpAccount(res.data);
        } catch (e) {
            handleError(e);
        }
    };


    const getBalance = async () => {
        try {
            setError(null);
            const res = await api.get(`/payment-service/${accountId}/balance`);
            setBalanceAccount({
                id: accountId,
                balance: res.data,
            });
        } catch (e) {
            handleError(e);
        }
    };


    const loadAccounts = async () => {
        try {
            setError(null);
            const res = await api.get(`/payment-service/accounts`);
            setAccounts(res.data);
        } catch (e) {
            handleError(e);
        }
    };


    return (
        <Card>
            <CardContent>
                <Stack spacing={4}>
                    <Typography variant="h5" align="center" color="primary">
                        Account Service
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

                        <Button variant="contained" onClick={createAccount}>
                            Create account
                        </Button>

                        <AccountTable
                            accounts={createdAccount ? [createdAccount] : []}
                        />
                    </CommandCard>


                    <CommandCard>
                        <TextField
                            label="Account ID"
                            fullWidth
                            onChange={(e) => setAccountId(e.target.value)}
                        />

                        <TextField
                            label="Sum"
                            fullWidth
                            onChange={(e) => setSum(e.target.value)}
                        />

                        <Button variant="contained" onClick={topUp}>
                            Top up
                        </Button>

                        <AccountTable
                            accounts={topUpAccount ? [topUpAccount] : []}
                        />
                    </CommandCard>


                    <CommandCard>
                        <TextField
                            label="Account ID"
                            fullWidth
                            onChange={(e) => setAccountId(e.target.value)}
                        />

                        <Button variant="contained" onClick={getBalance}>
                            Get balance
                        </Button>

                        <BalanceTable account={balanceAccount}/>
                    </CommandCard>


                    <CommandCard>
                        <Button variant="contained" onClick={loadAccounts}>
                            Reload all accounts
                        </Button>

                        <AccountTable accounts={accounts}/>
                    </CommandCard>
                </Stack>
            </CardContent>
        </Card>
    );
}
