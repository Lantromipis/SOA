import axios from "axios";
import {FLATS_API, SPONSOR_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";
import {Button, Form, InputNumber} from "antd";
import {validateIntegerGreaterThanZero} from "./validators";
import {useForm} from "antd/es/form/Form";
import {parseSponsorRequest} from "../../utils/parsers/xml/simple-requests-parser";

export function SponsorHouseForm() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [sponsorForm] = useForm();

    const handleFinish = (e) => {
        const body = parseSponsorRequest(e['amount'])

        axios.post(`${SPONSOR_API}/${e['houseId']}/${e['sponsorId']}`, body,
            {
                headers: {
                    'content-type': 'application/xml'
                }
            }
        )
            .then((response) => {
                    enqueueSnackbar(`Successfully sponsored house with id ${e['houseId']}`, {
                        autoHideDuration: 5000,
                        variant: "success"
                    })
                }
            )
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            })
    }

    return (
        <>
            <Form
                form={sponsorForm}
                onFinish={handleFinish}
                layout="inline"
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
            >
                <Form.Item
                    style={{width: 200}}
                    label="House ID"
                    name="houseId"
                    rules={[
                        {required: true, message: 'Please input ID!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item
                    style={{width: 200}}
                    label="Sponsor ID"
                    name="sponsorId"
                    rules={[
                        {required: true, message: 'Please input ID!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item
                    style={{width: 200}}
                    label="Amount"
                    name="amount"
                    rules={[
                        {required: true, message: 'Please input Amount!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={sponsorForm.submit} style={{width: 150}}>
                        Sponsor
                    </Button>
                </Form.Item>
            </Form>
        </>
    )
}