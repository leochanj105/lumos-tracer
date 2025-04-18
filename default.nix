{ pkgs ? import <nixpkgs> {}
, lumosTracingFramework
, mvnHash
}:

let
  inherit (pkgs)
    lib
    maven
    jdk8
    aspectj;
in
maven.buildMavenPackage {
  pname = "lumos-tracer";
  version = "0.0.1";

  src = ./.;

  inherit mvnHash;

  mvnParameters = lib.escapeShellArgs [
    "clean"
    "install"
    "package"
  ];

  mvnFetchExtraArgs = {
    # Includes packages from the tracing framework.
    preBuild = ''
      mkdir -p $out
      cp -dpR ${lumosTracingFramework.fetchedMavenDeps}/.m2 $out
      chmod +w -R $out/.m2
    '';
  };

  nativeBuildInputs = [
    jdk8
    aspectj
  ];

  mvnJdk = jdk8;

  postInstall = ''
    mv target $out
  '';
}
