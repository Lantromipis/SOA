import {Form, Input, InputNumber, Modal, Radio, Select} from "antd";
import {useForm} from "antd/es/form/Form";
import {useEffect} from "react";

export default function FlatForm({formVisible, onCancel, onFinish, title, initialValues}) {
    const [form] = useForm();

    useEffect(() => {
        if (initialValues) {
            form.setFieldsValue(initialValues)
        }
    }, [initialValues])

    return (
        <>
            <Modal
                title={title}
                open={formVisible}
                onOk={form.submit}
                onCancel={onCancel}
                width={1000}
            >
                <Form
                    form={form}
                    onFinish={onFinish}
                    labelCol={{span: 4}}
                    wrapperCol={{span: 8}}
                    layout="horizontal"
                >
                    <Form.Item
                        label="Price"
                        name="price"
                        rules={[
                            {required: true, message: 'Please input price!'},
                            () => ({
                                validator(_, value) {
                                    if (value >= 0) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('Price must be greater than 0!'));
                                },
                            }),
                        ]}
                    >
                        <InputNumber/>
                    </Form.Item>
                    <Form.Item
                        label="Area"
                        name="area"
                        rules={[
                            {required: true, message: 'Please input area!'},
                            () => ({
                                validator(_, value) {
                                    if (value >= 0) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('Area must be greater than 0!'));
                                },
                            }),
                        ]}
                    >
                        <InputNumber/>
                    </Form.Item>
                    <Form.Item
                        label="Number of rooms"
                        name="numberOfRooms"
                        rules={[
                            {required: true, message: 'Please input number of rooms!'},
                            () => ({
                                validator(_, value) {
                                    if (Number.isInteger(Number(value)) && value >= 0) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('Number of rooms must be integer greater than 0!'));
                                },
                            }),
                        ]}
                    >
                        <InputNumber/>
                    </Form.Item>
                    <Form.Item
                        label="Transport"
                        name="transport"
                        rules={[
                            {required: true, message: 'Please input transport!'}
                        ]}
                    >
                        <Select>
                            <Select.Option value="few">Few</Select.Option>
                            <Select.Option value="none">None</Select.Option>
                            <Select.Option value="little">Little</Select.Option>
                            <Select.Option value="normal">Normal</Select.Option>
                            <Select.Option value="enough">Enough</Select.Option>
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="New"
                        name="new"
                        rules={[{required: true, message: 'Please select if flat is new!'}]}
                    >
                        <Radio.Group>
                            <Radio value="true">Yes</Radio>
                            <Radio value="false">No</Radio>
                        </Radio.Group>
                    </Form.Item>
                    <Form.Item
                        label="Height"
                        name="height"
                        rules={[
                            {required: false, message: 'Please input height!'},
                            () => ({
                                validator(_, value) {
                                    if (!value) {
                                        return Promise.resolve();
                                    }
                                    if (Number.isInteger(Number(value)) && value >= 0) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('Height must be integer greater than 0!'));
                                },
                            }),
                        ]}
                    >
                        <InputNumber/>
                    </Form.Item>
                    <Form.Item
                        label="Name"
                        name="name"
                        rules={[
                            {required: true, message: 'Please input name!'},
                        ]}
                    >
                        <Input/>
                    </Form.Item>
                    <Form.Item label="Coordinates">
                        <Input.Group>
                            <Form.Item
                                label="X"
                                name={["coordinates", "x"]}
                                rules={[
                                    {required: true, message: 'Please input X!'},
                                    () => ({
                                        validator(_, value) {
                                            if (value >= -292) {
                                                return Promise.resolve();
                                            }
                                            return Promise.reject(new Error('X must be greater than -292!'));
                                        },
                                    }),
                                ]}
                            >
                                <InputNumber/>
                            </Form.Item>
                            <Form.Item
                                label="Y"
                                name={["coordinates", "y"]}
                                rules={[
                                    {required: true, message: 'Please input y!'},
                                    () => ({
                                        validator(_, value) {
                                            if (!value) {
                                                return Promise.resolve();
                                            }
                                            if (Number.isInteger(Number(value)) && value >= -747) {
                                                return Promise.resolve();
                                            }
                                            return Promise.reject(new Error('Y must be integer greater than -747!'));
                                        },
                                    }),
                                ]}
                            >
                                <InputNumber/>
                            </Form.Item>
                        </Input.Group>
                    </Form.Item>
                    <Form.Item label="House">
                        <Input.Group>
                            <Form.Item
                                label="Name"
                                name={["house", "name"]}
                                rules={[
                                    {required: true, message: 'Please input name!'},
                                ]}
                            >
                                <Input/>
                            </Form.Item>
                            <Form.Item
                                label="Year"
                                name={["house", "year"]}
                                rules={[
                                    {required: false, message: 'Please input year!'},
                                    () => ({
                                        validator(_, value) {
                                            if (!value) {
                                                return Promise.resolve();
                                            }
                                            if (Number.isInteger(Number(value)) && value >= 0) {
                                                return Promise.resolve();
                                            }
                                            return Promise.reject(new Error('Year must be integer greater than 0!'));
                                        },
                                    }),
                                ]}
                            >
                                <InputNumber/>
                            </Form.Item>
                            <Form.Item
                                label="Number of floors"
                                name={["house", "numberOfFloors"]}
                                rules={[
                                    {required: true, message: 'Please input number of floors!'},
                                    () => ({
                                        validator(_, value) {
                                            if (!value) {
                                                return Promise.resolve();
                                            }
                                            if (Number.isInteger(Number(value)) && value >= 0) {
                                                return Promise.resolve();
                                            }
                                            return Promise.reject(new Error('Number of floors must be integer greater than 0!'));
                                        },
                                    }),
                                ]}
                            >
                                <InputNumber/>
                            </Form.Item>
                            <Form.Item
                                label="Number of lifts"
                                name={["house", "numberOfLifts"]}
                                rules={[
                                    {required: false, message: 'Please input number of lifts!'},
                                    () => ({
                                        validator(_, value) {
                                            if (!value) {
                                                return Promise.resolve();
                                            }
                                            if (Number.isInteger(Number(value)) && value >= 0) {
                                                return Promise.resolve();
                                            }
                                            return Promise.reject(new Error('Number of lifts must be integer greater than 0!'));
                                        },
                                    }),
                                ]}
                            >
                                <InputNumber/>
                            </Form.Item>
                        </Input.Group>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
}