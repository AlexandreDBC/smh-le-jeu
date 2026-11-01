SRC_DIR = src
TEST_DIR = tests
BIN_DIR = bin
LIB_DIR = lib

LANTERNA = $(LIB_DIR)/lanterna-3.2.0-alpha1.jar
JUNIT = $(LIB_DIR)/junit-platform-console-standalone.jar

CP = $(LANTERNA)
TEST_CP = $(LANTERNA):$(JUNIT):$(BIN_DIR)

SOURCES = $(shell find $(SRC_DIR) -name "*.java")
TEST_SOURCES = $(shell find $(TEST_DIR) -name "*.java")

all: compile

compile:
	mkdir -p $(BIN_DIR)
	javac -cp $(CP) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SOURCES)

compile-tests: compile
	javac -cp $(TEST_CP) -d $(BIN_DIR) -sourcepath $(TEST_DIR) $(TEST_SOURCES)

run: compile
	java -cp $(BIN_DIR):$(CP) App

test: compile-tests
	java -jar $(JUNIT) --class-path $(BIN_DIR):$(CP) --scan-class-path

clean:
	rm -rf $(BIN_DIR)/*

.PHONY: all compile compile-tests run test clean
